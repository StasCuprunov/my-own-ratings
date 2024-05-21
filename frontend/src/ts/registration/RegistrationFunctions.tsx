import {useGet} from "../interface/useGet";
import {ROUTING_REGISTRATION, ROUTING_USERS_CREATE} from "../interface/APIConstants";
import {postAxios} from "../interface/BackendCalls";
import {User} from "../model/User";

export const useRegistration: any = () => {
    return useGet(ROUTING_REGISTRATION);
};

export const createUser: any = async (user: User) => {
    return await postAxios(ROUTING_USERS_CREATE, user);
};

export const getLabelEmailProps: any = () => {
    return {
        htmlFor: "email",
        text: "Email",
        sup: "1"
    };
};

export const getLabelFirstNameProps: any = () => {
    return {
        htmlFor: "first-name",
        text: "First name"
    };
};

export const getLabelSurnameProps: any = () => {
    return {
        htmlFor: "surname",
        text: "Surname"
    };
};

export const getLabelPasswordProps: any = () => {
  return {
      htmlFor: "password",
      text: "Password",
      sup: "1, 2"
  };
};

export const getLabelPasswordConfirmationProps: any = () => {
    return {
        htmlFor: "password-confirmation",
        text: "Password confirmation"
    };
};

export const getCreateAccountButtonProps: any = () => {
  return {
    type: "submit",
    text: "Create Account"
  };
};

export const getInputEmailProps: any = (value: string, maxLength: number, handleChange: any) => {
    return {
        required: true,
        name: "email",
        type: "email",
        value: value,
        maxLength: maxLength,
        onChange: handleChange
    };
};

export const getInputFirstNameProps: any = (value: string, maxLength: number, handleChange: any) => {
  return {
      name: "first-name",
      type: "text",
      value: value,
      maxlength: maxLength,
      onChange: handleChange
  }
};

export const getInputSurnameProps: any = (value: string, maxLength: number, handleChange: any) => {
    return {
        name: "surname",
        type: "text",
        value: value,
        maxLength: maxLength,
        onChange: handleChange
    };
};

export const getInputPasswordProps: any = (value: string, minLength: number, maxLength: number,
                                           handleChange: any) => {
   return {
       required: true,
       name: "password",
       type: "password",
       value: value,
       minLength: minLength,
       maxLength: maxLength,
       onChange: handleChange
   }
};

export const getInputPasswordConfirmationProps: any = (value: string, handleChange: any) => {
    return {
        required: true,
        name: "password-confirmation",
        type: "password",
        value: value,
        onChange: handleChange
    };
};

export const getInputErrorPasswordProps: any = (isPasswordInvalid: boolean,
                                                passwordErrors: string[]) => {
    return {
        condition: isPasswordInvalid,
        text: passwordErrors.toString()
    };
};

export const getInputErrorPasswordConfirmationProps: any =
    (isPasswordConfirmationInvalid: boolean) => {
        return {
            condition: isPasswordConfirmationInvalid,
            text: "The confirmation must be equal to the password."
        };
    };