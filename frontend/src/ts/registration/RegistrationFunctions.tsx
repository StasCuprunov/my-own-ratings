import {useGet} from "../interface/useGet";
import {ROUTING_REGISTRATION} from "../interface/APIConstants";

export const useRegistration: any = () => {
    return useGet(ROUTING_REGISTRATION);
};

export const getLabelEmailObject: any = () => {
    return {
        htmlFor: "email",
        text: "Email",
        sup: "1"
    };
};

export const getLabelFirstNameObject: any = () => {
    return {
        htmlFor: "first-name",
        text: "First name"
    };
};

export const getLabelSurnameObject: any = () => {
    return {
        htmlFor: "surname",
        text: "Surname"
    };
};

export const getLabelPasswordObject: any = () => {
  return {
      htmlFor: "password",
      text: "Password",
      sup: "1, 2"
  };
};

export const getLabelPasswordConfirmation: any = () => {
    return {
        htmlFor: "password-confirmation",
        text: "Password confirmation"
    };
};

export const getInputEmailObject: any = (value: string, maxLength: number, handleChange: any) => {
    return {
        required: true,
        name: "email",
        type: "email",
        value: value,
        maxLength: maxLength,
        onChange: handleChange
    };
};

export const getInputFirstNameObject: any = (value: string, maxLength: number, handleChange: any) => {
  return {
      name: "first-name",
      type: "text",
      value: value,
      maxlength: maxLength,
      onChange: handleChange
  }
};

export const getInputSurnameObject: any = (value: string, maxLength: number, handleChange: any) => {
    return {
        name: "surname",
        type: "text",
        value: value,
        maxLength: maxLength,
        onChange: handleChange
    };
};

export const getInputPasswordObject: any = (value: string, minLength: number, maxLength: number, handleChange: any) => {
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

export const getInputPasswordConfirmation: any = (value: string, handleChange: any) => {
    return {
        required: true,
        name: "password-confirmation",
        type: "password",
        value: value,
        onChange: handleChange
    };
};