package kr.bit.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// web.xml 대체 클래스 ! 반드시 상속 후 오버라이딩할 것.
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

//	1. root-context.xml -> RootConfig.java (db 정보)
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class, SecurityConfig.class };
	}

//	2. servlet-context.xml -> ServletConfig.java (frontcontroller)
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { ServletConfig.class };
	}

//	3. servlet-mapping -> "/" -> frontcontroller에서 모든 요청을 받겠다.
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	//	4. 한글 인코딩 필터
	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter };
    }

}
