import {ChangeEvent, FunctionComponent, useState} from "react";
import {Label} from "../component/atom/Label";
import {Input} from "../component/atom/Input";

export const RegistrationPage: FunctionComponent<any> = ({props}) => {
    const maxLengthString: number = props.maximumLengthOfString;
    const minLengthPassword: number = props.passwordMinimumLength;
    const maxLengthPassword: number = props.passwordMaximumLength;
    const atLeastOneDigitRegex: RegExp = new RegExp(props.atLeastOneDigitRegex);
    const atLeastOneEnglishUpperCaseLetterRegex: RegExp = new RegExp(props.atLeastOneEnglishUpperCaseLetterRegex);
    const atLeastOneEnglishLowerCaseLetterRegex: RegExp = new RegExp(props.atLeastOneEnglishLowerCaseLetterRegex);
    const atLeastOneValidSpecialCharacterRegex: RegExp = new RegExp(props.atLeastOneValidSpecialCharacterRegex);

    const [user, setUser] = useState({
        id: "",
        email: "",
        firstName: "",
        surname: "",
        password: ""
    });
    const [passwordConfirmation, setPasswordConfirmation] = useState("");

    const [isPasswordValid, setIsPasswordValid] = useState(true);
    const [isPasswordConfirmationValid, setIsPasswordConfirmationValid] =
        useState(true);

    const [passwordErrors, setPasswordErrors] = useState<string[]>([]);

    const handleUserChange = (field: string) => {
        return (e: ChangeEvent<HTMLInputElement>) => {
            setUser((prev) => ({
                ...prev,
                [field]: e.target.value
            }));
        };
    };

    const handlePasswordConfirmationChange = (event: any) => {
        setPasswordConfirmation(event.target.value);
    };

    const handleSubmit = (event: any) => {
        event.preventDefault();

        setIsPasswordValid(true);
        setIsPasswordConfirmationValid(true);

        checkPassword();
        if (passwordConfirmation !== user.password) {
            setIsPasswordConfirmationValid(false);
        }
        if (!isPasswordValid || !isPasswordConfirmationValid) {
            return;
        }
        console.log(user);
    };

    const checkPassword = ()=> {
        let password: string = user.password;
        let passwordErrors: string[] = [];
        let isPasswordValid: boolean = true;
        if (!atLeastOneDigitRegex.test(password)) {
            isPasswordValid = false;
            passwordErrors.push("Your password needs minimum one digit");
        }
        if (!atLeastOneEnglishUpperCaseLetterRegex.test(password)) {
            isPasswordValid = false;
            passwordErrors.push("Your password needs minimum one English uppercase letter");
        }
        if (!atLeastOneEnglishLowerCaseLetterRegex.test(password)) {
            isPasswordValid = false;
            passwordErrors.push("Your password needs minimum one English lowercase letter");
        }
        if(!atLeastOneValidSpecialCharacterRegex.test(password)) {
            isPasswordValid = false;
            passwordErrors.push("Your password needs minimum one of the following special characters: " +
                props.enumerationOfValidSpecialCharacters);
        }
        setIsPasswordValid(isPasswordValid);
        setPasswordErrors(passwordErrors);
    };
    let labelEmail: any = {
        htmlFor: "email",
        text: "Email",
        sup: "1"
    };
    let labelFirstName: any = {
        htmlFor: "first-name",
        text: "First name"
    };
    let labelSurname: any = {
        htmlFor: "surname",
        text: "Surname"
    };
    let labelPassword: any = {
        htmlFor: "password",
        text: "Password",
        sup: "1, 2"
    }
    let labelPasswordConfirmation: any = {
        htmlFor: "password-confirmation",
        text: "Password confirmation"
    };
    let inputEmail: any = {
        required: true,
        name: "email",
        type: "email",
        value: user.email,
        maxLength: maxLengthString,
        pattern: props.emailRegex,
        onChange: handleUserChange("email")
    };
    let inputFirstName: any = {
      required: true,
        name: "first-name",
        type: "text",
        value: user.firstName,
        maxlength: maxLengthString,
        onChange: handleUserChange("firstName")
    };
    let inputSurname: any = {
      name: "surname",
      type: "text",
      value: user.surname,
      maxLength: maxLengthString,
      onChange: handleUserChange("surname")
    };
    let inputPassword: any = {
        required: true,
        name: "password",
        type: "password",
        value: user.password,
        minLength: minLengthPassword,
        maxLength: maxLengthPassword,
        onChange: handleUserChange("password")
    };
    let inputPasswordConfirmation: any = {
        required: true,
        name: "password-confirmation",
        type: "password",
        value: passwordConfirmation,
        onChange: handlePasswordConfirmationChange
    };
    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h1>Registration</h1>
                <div>
                    <Label props={labelEmail}/>
                    <Input props={inputEmail}/>
                </div>
                <div>
                    <Label props={labelFirstName}/>
                    <Input props={inputFirstName}/>
                </div>
                <div>
                    <Label props={labelSurname}/>
                    <Input props={inputSurname}/>
                </div>
                <div>
                    <Label props={labelPassword}/>
                    <Input props={inputPassword}/>
                    {!isPasswordValid &&
                        <span>{passwordErrors.toString()}</span>
                    }
                </div>
                <div>
                    <Label props={labelPasswordConfirmation}/>
                    <Input props={inputPasswordConfirmation}/>
                    {!isPasswordConfirmationValid &&
                        <span>The confirmation must be equal to the password.</span>
                    }
                </div>
                <div>
                    <button type="submit">Create Account</button>
                </div>
                <div>
                    <h2>Hints</h2>
                    <ul>
                        <li>
                            <sup>1</sup>Required
                        </li>
                        <li><sup>2</sup>
                            The password must contain minimum one digit, one uppercase English letter, one lowercase
                            English letter, and one of the following special
                            characters: {props.enumerationOfValidSpecialCharacters}
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    );
}
