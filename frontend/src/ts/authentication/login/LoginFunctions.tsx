import {LoginModel} from "../../model/LoginModel";
import {postAxios} from "../../interface/BackendCalls";
import {API_ROUTING_LOGIN} from "../../constant/routing/APIConstants";

export const login = async (login: LoginModel) => {
    return await postAxios(API_ROUTING_LOGIN, login);
};

export const getEmailLabelProps = () => {
    return {
        htmlFor: "email",
        text: "Email"
    }
};

export const getPasswordLabelProps = () => {
    return {
        htmlFor: "password",
        text: "Password"
    }
};

export const getLoginButtonProps = () => {
    return {
      type: "submit",
      text: "Login"
    };
};

export const getInputEmailProps = (value: string, handleChange: any) => {
    return {
        required: true,
        name: "email",
        type: "email",
        value: value,
        onChange: handleChange
    };
};

export const getInputPasswordProps = (value: string, handleChange: any) => {
    return {
        required: true,
        name: "password",
        type: "password",
        value: value,
        onChange: handleChange
    };
};