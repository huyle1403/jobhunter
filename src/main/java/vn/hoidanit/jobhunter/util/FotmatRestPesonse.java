package vn.hoidanit.jobhunter.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletResponse;
import vn.hoidanit.jobhunter.domain.RestResponse;

@ControllerAdvice
public class FotmatRestPesonse implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {

        // Ép kiểu để lấy status HTTP
        if (response instanceof ServletServerHttpResponse servletServerResponse) {
            HttpServletResponse servletResponse = servletServerResponse.getServletResponse();
            int status = servletResponse.getStatus();

            RestResponse<Object> res = new RestResponse<>();
            res.setStatusCode(status);
            if (body instanceof String) {
                return body;
            }
            if (status >= 400) {
                res.setError("call API failed");
                res.setMessage(body);

            } else {
                res.setData(body);
                res.setMessage("call api failed");
            }
            return res;
        }

        return body; // trả nguyên body gốc nếu không muốn thay đổi
    }

}
