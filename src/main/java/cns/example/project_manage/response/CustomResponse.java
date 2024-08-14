package cns.example.project_manage.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomResponse<T> {
    public ResponseModel error(Exception ex){
        return new ResponseModel(0,ex.getMessage());
    }

    public ResponseModel successText(String message){
        return new ResponseModel(1,message);
    }

    public ResponseModel success(T data){
        return new ResponseModel(200, "success", data);
    }
}