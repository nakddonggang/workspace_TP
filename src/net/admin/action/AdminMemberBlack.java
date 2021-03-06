package net.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminDAO;
import net.member.db.MemberDAO;
import net.member.db.MemberDTO;
import util.actionForward.Action;
import util.actionForward.ActionForward;

public class AdminMemberBlack implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		
		System.out.println("AdminMemberBlack execute()");
		request.setCharacterEncoding("utf-8");
		
		
		AdminDAO adao = new AdminDAO();
		int count = adao.getGoodMemberCount();
		int pageSize = 5;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;	
		
		List<MemberDTO> memberList = null;
		
		if(count !=0){
			memberList = adao.getMemberList(startRow, pageSize);
		}
		
		int pageCount = count/pageSize+(count%pageSize==0? 0:1);
		int pageBlock = 5;
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		
		int endPage = startPage+pageBlock-1;
		if(endPage > pageCount){
			endPage = pageCount;	
		}
		
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("memberList", memberList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admin/admMemBlack.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}