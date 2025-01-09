package com.example.hiddenplace.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
    이 클래스는 JWT로그인 방식의 UPAF필터를 커스텀하는 필터 클래스 입니다.
    UPA Token을 활용하여 Manager에게 request에서 가로챈 ID, PW를 던져주며,
    Manager가 검증에 성공한다면 성공 메서드를 자동으로 호출합니다.
    API요청 시에 컨트롤러에 login경로를 등록하지 않아도, UPAF가 캐치해서 처리합니다
 */

//spring security의 form로그인 방식을 disable했기 때문에, form 로그인에 해당하는 UPAF를 사용할 수 없다
//직접 우리가 커스텀하여서 UPAF를 사용해야 한다.
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override //?
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println(username);
        System.out.println(password);
        //Manager한테 넘겨줄 때, UPA Token에 넘겨줘야하기 때문에 담아준다. 3번 째 인자는 Role 값
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        //Token 넘길 때, authenticate 메서드 사용
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공 시, 자동 호출
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
    }

    //로그인 실패 시, 자동 호출
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

    }
}
