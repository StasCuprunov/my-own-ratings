import {ChangeEvent, FunctionComponent, useState} from "react";
import {login} from "./LoginFunctions";
import {getInputEmailProps, getInputPasswordProps} from "./LoginFunctions";
import {LoginPage} from "./LoginPage";
import {LoginModel} from "../../model/LoginModel"
import {useAuth} from "../../context/AuthContext";
import {useNavigate} from "react-router-dom";
import {WEBSITE_ROUTING_INDEX} from "../../constant/WebsiteRoutingConstants";
import {setCookieHasLoggedInRecentlyAsRole} from "../../utility/CookieUtility";

export const Login: FunctionComponent<any> = () => {
    const navigate = useNavigate();
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

        const {data, error} = await login(loginData);

        if (error) {
            setBackendError(error);
            return;
        }
        setCookieHasLoggedInRecentlyAsRole(data.roles, data.expirationTimeInSeconds);
        setAuthenticated(true);
        navigate(WEBSITE_ROUTING_INDEX);
    };

    let inputEmail: any = getInputEmailProps(loginData.email, handleChange("email"));
    let inputPassword: any = getInputPasswordProps(loginData.password, handleChange("password"));

    return (
        <LoginPage backendError={backendError} handleSubmit={handleSubmit} inputEmail={inputEmail}
                   inputPassword={inputPassword}
        />
    );
};