import {FunctionComponent, useEffect, useState} from "react";
import {getEmailLabelProps, getPasswordLabelProps, login} from "./LoginFunctions";
import {getInputEmailProps, getInputPasswordProps} from "./LoginFunctions";
import {LoginPage} from "./LoginPage";
import {LoginModel} from "../../model/LoginModel"
import {useAuth} from "../../context/AuthContext";
import {useNavigate} from "react-router-dom";
import {WEBSITE_ROUTING_INDEX, WEBSITE_ROUTING_REGISTRATION} from "../../constant/routing/WebsiteRoutingConstants";
import {setCookieHasLoggedInRecentlyAsRole} from "../../utility/CookieUtility";
import {handleChange} from "../../utility/FormUtility";
import {PageTemplate} from "../../component/PageTemplate";
import {setSiteTitle} from "../../utility/WebsiteUtility";

const labelEmail: any = getEmailLabelProps();
const labelPassword: any = getPasswordLabelProps();

export const Login: FunctionComponent<any> = () => {
    const navigate = useNavigate();
    const [loginData, setLoginData] = useState(new LoginModel());
    const [backendError, setBackendError] = useState(null);
    const {setAuthenticated} = useAuth();

    useEffect(() => {
        setSiteTitle("login");
    }, []);

    const handleLoginDataChange = (field: string) => {
        return handleChange(field, setLoginData);
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

    let inputEmail: any = getInputEmailProps(loginData.email, handleLoginDataChange("email"));
    let inputPassword: any = getInputPasswordProps(loginData.password, handleLoginDataChange("password"));

    const formForEmail: any = {
        label: labelEmail,
        input: inputEmail
    };

    const formForPassword: any = {
        label: labelPassword,
        input: inputPassword
    };

    const linkToRegistration: any = {
        textBefore: "Don't you have an account?",
        to: WEBSITE_ROUTING_REGISTRATION,
        textLink: "Sign up here"
    };

    const pageProps: any = {
        backendError: backendError,
        handleSubmit: handleSubmit,
        formForEmail: formForEmail,
        formForPassword: formForPassword,
        linkToRegistration: linkToRegistration
    };

    return (
        <PageTemplate Component={LoginPage} props={pageProps}/>
    );
};