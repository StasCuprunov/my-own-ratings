import {ChangeEvent, FunctionComponent, useState} from "react";

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

    const onChangeHandler = (event: any) => {
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


    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h1>Registration</h1>
                <div>
                    <label htmlFor="email">Email<sup>1</sup></label>
                    <input required name="email" type="email" value={user.email} maxLength={maxLengthString}
                           pattern={props.emailRegex} onChange={handleUserChange("email")}/>
                </div>
                <div>
                    <label htmlFor="first-name">First name</label>
                    <input name="first-name" type="text" value={user.firstName} maxLength={maxLengthString}
                    onChange={handleUserChange("firstName")}/>
                </div>
                <div>
                    <label htmlFor="surname">Surname</label>
                    <input name="surname" type="text" value={user.surname} maxLength={maxLengthString}
                           onChange={handleUserChange("surname")}/>
                </div>
                <div>
                    <label htmlFor="password">Password<sup>1, 2</sup></label>
                    <input name="password" required type="password" value={user.password}
                           onChange={handleUserChange("password")} minLength={minLengthPassword}
                           maxLength={maxLengthPassword}/>
                    {!isPasswordValid &&
                        <span>{passwordErrors.toString()}</span>
                    }
                </div>
                <div>
                    <label htmlFor="password-confirmation">Password confirmation</label>
                    <input required name="password-confirmation" type="password" value={passwordConfirmation}
                        onChange={onChangeHandler}
                    />
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
