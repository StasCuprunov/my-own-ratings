import {LoginModel} from "../model/LoginModel";
import {postAxios} from "../interface/BackendCalls";
import {ROUTING_LOGIN} from "../interface/APIConstants";

export const login = async (login: LoginModel) => {
    return await postAxios(ROUTING_LOGIN, login);
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