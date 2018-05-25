package com.mood.base;


import com.mood.common.HttpCode;
import com.mood.content.OauthContants;
import com.mood.entity.user.User;
import com.mood.redis.CacheService;
import com.mood.utils.DateUtil;
import lombok.val;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

@Controller
public class BaseController {

	@Autowired
	private CacheService simpleCacheService;


	public static final String project_id = "640FAAA324A25C3AE41B9D9A6658DAD2";

	public static final String role_admin_name = "ROLE_ADMIN";

	public User getLoginUser(HttpServletRequest request) {
		String access_token = request.getParameter("access_token");
		User member = (User)simpleCacheService.get(OauthContants.ACCESS_TOKEN + access_token.toString());

		return member;
	}

	// 获取登录用户id
	public String getUserId(HttpServletRequest request) {
		User user = getLoginUser(request);
		if(user != null) {
			return user.getId();
		}
		return null;
	}

	protected ResponseEntity<?> buildResponseEntity(HttpCode errorEnum, BaseVo vo) {
		if (vo == null) {
			vo = new BaseVo();
		}
		vo.code = errorEnum.getCode();
		vo.msg = errorEnum.getMsg();
		return new ResponseEntity<BaseVo>(vo, HttpStatus.OK);
	}

	protected ResponseEntity<?> buildResponseEntity(HttpCode errorEnum) {
		BaseVo vo = new BaseVo();
		return buildResponseEntity(errorEnum, vo);
	}

	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap,Map<String,?> data){
		return setModelMap(modelMap, HttpCode.SUCCESS, data);
	}

	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap,Object... data){
		return setModelMap(modelMap, HttpCode.SUCCESS, data);
	}

	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap,Object data){
		return setModelMap(modelMap, HttpCode.SUCCESS, data);
	}

	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, List<?> data){
		return setModelMap(modelMap, HttpCode.SUCCESS, data);
	}

	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Serializable data){
		return setModelMap(modelMap, HttpCode.SUCCESS, data);
	}

	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object... data){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.putAll(modelMap);
		modelMap.clear();
		Iterator<String> iterator = map.keySet().iterator();
		if (data != null) {
			modelMap.put("data", data);
		} else {
			modelMap.put("data", new JSONObject());
		}
		modelMap.put("code", code.getCode());
		modelMap.put("message", code.getMsg());
		modelMap.put("timestamp", DateUtil.getCurrentTime());
		return ResponseEntity.ok(modelMap);
	}

//	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Serializable data){
//		Map<String, Object> map = new LinkedHashMap<String, Object>();
//		map.putAll(modelMap);
//		modelMap.clear();
//		Iterator<String> iterator = map.keySet().iterator();
//		while (iterator.hasNext()) {
//			String key = iterator.next();
//			if (!key.startsWith("org.springframework.validation.BindingResult") && key != "void") {
//				modelMap.put(key, map.get(key));
//			}
//		}
//		if (data != null) {
//			try {
//				modelMap.put("data", JSONObject.fromObject(data.toString()));
//			} catch(Exception e) {
//				modelMap.put("data", data);
//			}
//		} else {
//			modelMap.put("data", new JSONObject());
//		}
//		modelMap.put("code", code.getCode());
//		modelMap.put("message", code.getMsg());
//		modelMap.put("timestamp", System.currentTimeMillis());
//		return ResponseEntity.ok(modelMap);
//	}

//	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, List<?> data){
//		modelMap.clear();
//		if (data != null) {
//			modelMap.put("data", data);
//			modelMap.put("total", data.size());
//		} else {
//			modelMap.put("data", new JSONObject());
//		}
//		modelMap.put("code", code.getCode());
//		modelMap.put("message", code.getMsg());
//		modelMap.put("timestamp", System.currentTimeMillis());
//		return ResponseEntity.ok(modelMap);
//	}


	public <T> T po2vo(final T source, final T target) {
		BeanUtils.copyProperties(source, target);
		return target;
	}


}
