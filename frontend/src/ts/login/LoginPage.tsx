import {FunctionComponent} from "react";
import {Label} from "../component/atom/form/Label";
import {Input} from "../component/atom/form/Input";
import {Button} from "../component/atom/button/Button";
import {getEmailLabelProps, getLoginButtonProps, getPasswordLabelProps} from "./LoginFunctions";
import {ErrorPage} from "../general-page/ErrorPage";

const labelEmail: any = getEmailLabelProps();
const labelPassword: any = getPasswordLabelProps();
const loginButton: any = getLoginButtonProps();

export const LoginPage: FunctionComponent<any> = ({backendError, handleSubmit, inputEmail, inputPassword}) => {

    if (backendError) {
        return (
            <ErrorPage error={backendError}/>
        );
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h1>Login</h1>
                <div>
                    <Label props={labelEmail}/>
                    <Input props={inputEmail}/>
                </div>
                <div>
                    <Label props={labelPassword}/>
                    <Input props={inputPassword}/>
                </div>
                <div>
                    <Button props={loginButton}/>
                </div>
            </form>
        </div>
    );
};