package com.jd.userinfo;

import java.io.*;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jd.userinfo.service.IUserInfoService;
import com.jd.util.PropertiesUtil;
import com.jd.vo.UserInfo;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserInfoController {
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@RequestMapping("userInfoList.do")
	public String userInfoList() {
		return "userInfo/userInfoList";
	}
	
	@RequestMapping("userInfoListFrag.do")
	public ModelAndView userInfoListFrag(int pageNo, String name, String mobile) {
//		视图
		ModelAndView mv = new ModelAndView("userInfo/userInfoListFrag");
//		数据
		mv.addObject("list", userInfoService.page(pageNo, name, mobile));
		mv.addObject("pageTotal", userInfoService.pageTotal(name, mobile));
//		返回数据和视图
		return mv;
	}

	@ResponseBody//异步获取json数据，加上@responsebody后，会直接返回json数据
	@RequestMapping("delete.do")
	public String delete(String id) {
		if(userInfoService.delete(id)>0) {
			return "{\"i\":\"string\"}";
		}
		return "0";
//		在前台接收到的数据为：'{"xxx":"xxx"}'
	}
	
	@RequestMapping("addView.do")
	public String addView() {
		return "userInfo/userInfoAdd";
	}
	
	@ResponseBody
	@RequestMapping("add.do")
	public String add(UserInfo userInfo, @RequestParam("image")MultipartFile image) {
//		新文件名等于32位UUID＋原文件后缀
		String fileName = image.getOriginalFilename();
		if(!"".equals(fileName)&&fileName!=null) {
			upload(userInfo, image);
		}
		if(userInfoService.add(userInfo)>0) {
			return "1";
		}
		return "0";
	}
	
	@RequestMapping("select.do")
	public ModelAndView select(String id) {
		ModelAndView mv = new ModelAndView("userInfo/userInfoSelect");
		mv.addObject("userInfo", userInfoService.get(id));
		mv.addObject("fileServer", PropertiesUtil.getValue("file.server"));
		return mv;
	}
	
	@RequestMapping("updateView.do")
	public ModelAndView updateView(String id) {
		ModelAndView mv = new ModelAndView("userInfo/userInfoUpdate");
		mv.addObject("userInfo", userInfoService.get(id));//从数据库中获取userInfo对象各属性放入请求里，再用data:new FormData(document.getElementById("add"))或$("#").serialize()传入Controller，
		mv.addObject("fileServer", PropertiesUtil.getValue("file.server"));
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("update.do")
	public String update(UserInfo userInfo, @RequestParam("image")MultipartFile image) {
//		新文件名等于32位UUID＋原文件后缀
		String fileName = image.getOriginalFilename();
		if(!"".equals(fileName)&&fileName!=null) {//修改了头像
			System.out.println("userInfo:"+userInfo);
			String oldFileName = userInfo.getImg();
			System.out.println("oldFileName:"+oldFileName);
			if(!"".equals(oldFileName)&&oldFileName!=null) {//覆盖原头像
				File oldFile = new File("D:"+File.separator+"images"+File.separator+oldFileName);
				if(oldFile.exists()&&oldFile.delete()) {
					upload(userInfo, image);
				}
			}else {//覆盖默认头像（无删除）
				upload(userInfo, image);
			}
		}else if("".equals(fileName)) {//原来使用的是默认头像，本次没有进行修改
		      userInfo.setImg(null);
	    }
		if(userInfoService.update(userInfo)>0) {
			return "1";
		}
		return "0";
	}
	
	private void upload(UserInfo userInfo, MultipartFile image) {
		String fileName = image.getOriginalFilename();
		fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf('.'));
//		将上传的文件复制到指定路径
		try {
			FileOutputStream outputStream = new FileOutputStream("D:"+File.separator+"images"+File.separator+fileName);
			IOUtils.copy(image.getInputStream(), outputStream);
			IOUtils.closeQuietly(outputStream);
			System.out.println("upload-fileName:"+fileName);
			userInfo.setImg(fileName);
			System.out.println("upload-userInfo:"+userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
