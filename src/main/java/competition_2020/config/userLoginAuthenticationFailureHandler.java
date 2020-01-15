package competition_2020.config;
import java.io.PrintWriter;
import	java.sql.ResultSet;

import competition_2020.Util.JsonUtil;
import competition_2020.pojo.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class userLoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Result result=new Result(false,159,"haha");
        if(exception.getMessage().equals("not exist")){
            result.setCode(402);
            result.setMessage("not exist");
            result.setFlag(false);
        }
        String json = JsonUtil.generate(result);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.write(json);
        out.flush();
        out.close();
    }
}
