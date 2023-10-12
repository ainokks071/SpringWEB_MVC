package kr.narp.myapp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {
	
/*	1. 회원 아이디, 회원 이름, 파일1, 파일2 data 넘어온다 ! -> 수집 필요 
 * 	  - 일반문자열 + 파일데이터(바이너리데이터) 넘어온다 -> 이럴 땐, MultipartHttpServletRequest (O)   	*/  
	@RequestMapping("/uploadFiles.do")
	public String uploadFiles(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, Model model)
	throws IOException {
		
//		web.xml에서 인코딩 필터 처리했음 ! request.setCharacterEncoding("utf-8"); (필요 X)
		
//	2.	최종 업로드 폴더(업로드되는 파일들의 저장폴더)의 이름
		String UPLOAD_DIR = "file_repo";
//	 	업로드 폴더의 실제 물리적 경로 이름 
		String uploadPath = request.getServletContext().getRealPath(UPLOAD_DIR);
// 		Users/kks/dev-study/ServletJSP/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVC02/file_repo
		
//		System.out.println(uploadPath);
		
//	3.	업로드 폴더의 실제경로로 File객체 만들기
//		File uploadFolder = new File(uploadPath);
//		그 경로에 폴더 존재하지 않으면 업로드 폴더 만들어라.
//		if(!uploadFolder.exists()) {
//			uploadFolder.mkdir();
//		}
		
//	4.	다운로드 페이지로 포워딩할 map데이터 
		Map map = new HashMap<String, String>(); // (Key, Value)쌍으로 데이터를 맵에 저장할 것.
		
//		form내의 파라메터 변수이름을 알고 있을 때. 귀찮다.
//		String id = multipartRequest.getParameter("id"); //form태그의 name속성 = "id" : kimks071 
//		String name = multipartRequest.getParameter("name"); //form태그의 name속성 = "name" : 김경수 
//		System.out.println("id : " + id + ", name : " + name);
		
//	5.	form내의 파라메터 변수이름을 몰라도 가져올 수 있다. + 입력데이터 가져오
//		나열, 열거. 마치 배열. [id, name, ...] 주의) 파일은 읽지 못한다.
		Enumeration<String> enumeration = multipartRequest.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement(); // "id", "name"
			String value = multipartRequest.getParameter(paramName); // kimks071, 김경수
//			System.out.println(paramName + " : " + value);
			map.put(paramName, value); //{id=kimks071, name=김경수}
		}
		
//	6.	얘도 열거형.
		Iterator<String> it = multipartRequest.getFileNames(); //파일이 담긴 파라메터이름 [file1, file2 ...]
		List<String> fileNameList = new ArrayList<String>();
		while (it.hasNext()) {
			String paramName = it.next(); // file1
			MultipartFile mFile = multipartRequest.getFile(paramName); //파일이 담겨있는 클래스, 파일 정보 제공.
			String fileName = mFile.getOriginalFilename(); //사용자가 업로드한 파일 이름 : user01.jpg
			fileNameList.add(fileName);
			
//	7.	지정한 경로에 폴더, 파일 생성되는지 테스트해보기.
//			파일객체생성 : 업로드할 폴더경로 내 파일 
			File uploadFile = new File(uploadPath + "/" + paramName);
			
			if(mFile.getSize() != 0) {
				if(!uploadFile.exists()) {
//					/SpringMVC02/file_repo/file1
//					mkdir() : 상위폴더(여기서는 SpringMVC02폴더)있을때만 폴더생성가능 
//					mkdirs() : 상위폴더(여기서는 SpringMVC02폴더)없어도 폴더생성가능 
					if(uploadFile.getParentFile().mkdir()) {
						uploadFile.createNewFile(); //임시로 파일하나를 생성한다. 
					}
				}
//	8.		지정된 경로에 실제로 파일업로드하기.
				mFile.transferTo(new File(uploadPath + "/" + fileName));
			}
			
		}
//		맵객체 안에 리스트객체 삽입 [{ key , value }, { key  , value }, { key , listValue(data, data, data) }] 
		map.put("fileNameList", fileNameList);
		
//		객체바인딩
		model.addAttribute("map", map);
		
//		System.out.println(map.toString()); //{name=김경수, file2=user02.jpeg, file3=user03.png, id=kimks071, file1=user01.jpeg}
		
//		포워딩
		return "fileResult";
	}

}
