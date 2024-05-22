import {ChangeEvent, FunctionComponent, useState} from "react";
import {login} from "./LoginFunctions";
import {getInputEmailProps, getInputPasswordProps} from "./LoginFunctions";
import {LoginPage} from "./LoginPage";
import {LoginModel} from "../model/LoginModel"
import {useAuth} from "../context/AuthContext";

export const Login: FunctionComponent<any> = () => {
    const [loginData, setLoginData] = useState(new LoginModel());
    const [backendError, setBackendError] = useState(null);
    const {setAuthenticated} = useAuth();

    const handleChange = (field: string) => {
        return (e: ChangeEvent<HTMLInputElement>) => {
            setLoginData((prev: any) => ({
                ...prev,
                [field]: e.target.value
            }));
        };
    };

    const handleSubmit = async (event: any) => {
        event.preventDefault();

        const {error} = await login(loginData);

        if (error) {
            setBackendError(error);
            return;
        }
        setAuthenticated(true);
    };

    let inputEmail: any = getInputEmailProps(loginData.email, handleChange("email"));
    let inputPassword: any = getInputPasswordProps(loginData.password, handleChange("password"));

    return (
        <LoginPage backendError={backendError} handleSubmit={handleSubmit} inputEmail={inputEmail}
                   inputPassword={inputPassword}
        />
    );
};