import {FunctionComponent} from "react";
import {Label} from "../../component/atom/form/Label";
import {Input} from "../../component/atom/form/input/Input";
import {CustomButton} from "../../component/atom/button/CustomButton";
import {ButtonLink} from "../../component/atom/button/link/ButtonLink";
import {
    getEmailLabelProps,
    getLoginButtonProps,
    getPasswordLabelProps,
    registrationButtonLinkProps
} from "./LoginFunctions";
import {Error} from "../../general-page/error/Error";

const labelEmail: any = getEmailLabelProps();
const labelPassword: any = getPasswordLabelProps();
const loginButton: any = getLoginButtonProps();

export const LoginPage: FunctionComponent<any> = ({backendError, handleSubmit, inputEmail, inputPassword}) => {

    if (backendError) {
        return (
            <Error error={backendError}/>
        );
    }

    return (
        <div>
            <h1>Login</h1>
            <div>
                <form onSubmit={handleSubmit}>
                    <div>
                        <Label props={labelEmail}/>
                        <Input props={inputEmail}/>
                    </div>
                    <div>
                        <Label props={labelPassword}/>
                        <Input props={inputPassword}/>
                    </div>
                    <div>
                        <CustomButton props={loginButton}/>
                    </div>
                </form>
            </div>
            <div>
                <h2>Don't you have an account?</h2>
                <ButtonLink props={registrationButtonLinkProps()}/>
            </div>
        </div>
    );
};