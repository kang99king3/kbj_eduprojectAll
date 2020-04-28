package com.hk.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.daos.LoginDao;
import com.hk.dtos.LoginDto;

@WebServlet("/LoginController.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String command=request.getParameter("command");

		LoginDao dao=new LoginDao();
		
		HttpSession session=request.getSession();
		
		if(command.equals("regist")){
			response.sendRedirect("regist.jsp");
		}else if(command.equals("insertuser")){
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			String address=request.getParameter("address");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			
			boolean isS=dao.insertUser(
					new LoginDto(id,name,password,address,phone,email) );
			
			if(isS){
				request.setAttribute("msg", "회원가입이 되었습니다.");
				dispatch("index.jsp",request,response);
			}else{
				request.setAttribute("msg", "회원가입에 실패하였습니다.");
				dispatch("error.jsp",request,response);
			}
		}else if(command.equals("login")){
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			
			LoginDto ldto=dao.getLogin(id, password);
			
			if(ldto==null||ldto.getId()==null){
				request.setAttribute("msg", "아이디나 패스워드를 확인하세요!");
				dispatch("error.jsp",request,response);
			}else{
				//id와password가 맞다면!
				session.setAttribute("ldto", ldto);//session스코프에 담기
				session.setMaxInactiveInterval(10*60);//10분간 요청이 없으면 세션을 삭제
				
				//등급(admin,manager,user)을 확인해서 해당 Main 페이지로 이동하기
				if(ldto.getRole().toUpperCase().equals("ADMIN")){
					response.sendRedirect("admin_main.jsp");
				}else if(ldto.getRole().toUpperCase().equals("MANAGER")){
					response.sendRedirect("user_main.jsp");
				}else{
					response.sendRedirect("user_main.jsp");
				}
					
			}
		}else if(command.equals("logout")){
			session.invalidate();//session안에 객체들을 모두 삭제
			response.sendRedirect("index.jsp");
		}else if(command.equals("idChk")){
			String id=request.getParameter("id");
			String resultId=dao.idCheck(id);//값이 null?, 값이 있냐? 
			//request 스코프에 저장(   key    ,   value)
			request.setAttribute("resultId", resultId);
			dispatch("idchkform.jsp",request,response);
		}else if(command.equals("userinfo")){
			String id=request.getParameter("id");
			LoginDto dto=dao.getUserInfo(id);
			request.setAttribute("dto", dto);
			dispatch("userdetail.jsp",request,response);
		}else if(command.equals("updateForm")){
			String id=request.getParameter("id");//파라미터 받고
			LoginDto dto=dao.getUserInfo(id);//dao 메서드 실행
			request.setAttribute("dto", dto);//결과 scope에 담고
			dispatch("userUpdateInfo.jsp",request,response);//페이지 이동
		}else if(command.equals("userupdate")){
			String id=request.getParameter("id");
			String address=request.getParameter("address");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			
			boolean isS=dao.updateUserInfo(new LoginDto(id,address,phone,email));
			if(isS){
				response.sendRedirect("LoginController.do?command=userinfo&id="+id);
			}else{
				request.setAttribute("msg", "정보수정실패");
				dispatch("error.jsp",request,response);
			}
		}else if(command.equals("delUser")){//회원 탈퇴
			String id=request.getParameter("id");
			boolean isS=dao.delUser(id);
			if(isS){
				session.invalidate();//회원탈퇴했으니 로그아웃해야 되서
				response.sendRedirect("index.jsp");
			}else{
				request.setAttribute("msg", "탈퇴 실패");
				dispatch("error.jsp",request,response);
			}
		}else if(command.equals("allUserStatus")){
			List<LoginDto> list=dao.getAllUserStatus();
			request.setAttribute("list", list);
			dispatch("allUserList.jsp",request,response);
		}else if(command.equals("allUserList")){
			List<LoginDto> list=dao.getAllUserList();
			request.setAttribute("list", list);
			dispatch("userList.jsp",request,response);
		}else if(command.equals("roleForm")){
			//request.getParameter("name"); 로 받아야 하는 경우는 아래와 같다
			// get방식: controller.jsp?id=aa
			// post방식: <form action="controller.jsp">
			//             <input tpye="text" name=id"/>
			String id=request.getParameter("id");
			LoginDto dto=dao.getUserInfoRole(id);
			request.setAttribute("dto", dto);
			dispatch("userUpdateRole.jsp",request,response);
		}else if(command.equals("roleChange")){
			String id=request.getParameter("id");
			String role=request.getParameter("role");
			boolean isS=dao.userUpdateRole(id, role);
			if(isS){
				response.sendRedirect("LoginController.do?command=allUserList");
			}else{
				request.setAttribute("msg", "등급변경실패");
				dispatch("error.jsp",request,response);
			}
		}
	}

	public void dispatch(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
}




