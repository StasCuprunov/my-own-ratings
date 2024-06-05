import {useNavigate} from "react-router-dom";
import {PasswordRegex} from "./PasswordRegex";
import {FunctionComponent, useMemo, useState, useEffect} from "react";
import {User} from "../model/User";
import {
    createUser,
    getInputEmailProps,
    getInputErrorPasswordConfirmationProps,
    getInputErrorPasswordProps,
    getInputFirstNameProps,
    getInputPasswordConfirmationProps,
    getInputPasswordProps,
    getInputSurnameProps,
    getLabelEmailProps,
    getLabelFirstNameProps,
    getLabelPasswordConfirmationProps,
    getLabelPasswordProps,
    getLabelSurnameProps
} from "./RegistrationFunctions";
import {WEBSITE_ROUTING_INDEX} from "../constant/routing/WebsiteRoutingConstants";
import {RegistrationPage} from "./RegistrationPage";
import {handleChange} from "../utility/FormUtility";
import {PageTemplate} from "../component/PageTemplate";

const labelEmail: any = getLabelEmailProps();
const labelFirstName: any = getLabelFirstNameProps();
const labelSurname: any = getLabelSurnameProps();
const labelPassword: any = getLabelPasswordProps();
const labelPasswordConfirmation: any = getLabelPasswordConfirmationProps();

export const Registration: FunctionComponent<any> = ({props}) => {
    const navigate = useNavigate();
    const maxLengthString: number = props.maximumLengthOfString;

    const passwordRegex: PasswordRegex = useMemo(() => new PasswordRegex(props.atLeastOneDigitRegex,
        props.atLeastOneEnglishUpperCaseLetterRegex, props.atLeastOneEnglishLowerCaseLetterRegex,
        props.atLeastOneValidSpecialCharacterRegex, props.enumerationOfValidSpecialCharacters), [])

    const [user, setUser] = useState(new User());
    const [passwordConfirmation, setPasswordConfirmation] = useState("");

    const [isPasswordValid, setIsPasswordValid] = useState(true);
    const [isPasswordConfirmationValid, setIsPasswordConfirmationValid] =
        useState(true);

    const [passwordErrorText, setPasswordErrorText] =
        useState<string>("");
    const [backendError, setBackendError] = useState(null);

    const handleUserChange = (field: string) => {
        return handleChange(field, setUser);
    };

    const handlePasswordConfirmationChange = (event: any) => {
        setPasswordConfirmation(event.target.value);
    };

    const handleSubmit = async (event: any) => {
        event.preventDefault();

        if (!isPasswordValid || !isPasswordConfirmationValid) {
            return;
        }
        let result: any = await createUser(user);

        const {error} = result;
        if (error) {
            setBackendError(error);
        }
        else {
            navigate(WEBSITE_ROUTING_INDEX);
        }
    };

    useEffect(() => {
        checkPasswordConfirmation();
    }, [passwordConfirmation]);

    useEffect(() => {
        checkPassword();
    }, [user.password]);

    const checkPassword = () => {
        const { isPasswordValid, passwordErrorText } = passwordRegex.checkPassword(user.password);
        setIsPasswordValid(isPasswordValid);
        setPasswordErrorText(passwordErrorText);
    };

    const checkPasswordConfirmation = () => {
        let isValid: boolean = true;
        if (passwordConfirmation !== user.password) {
            isValid = false;
        }
        setIsPasswordConfirmationValid(isValid);
    };

    const inputEmail: any = useMemo(() =>
            getInputEmailProps(user.email, maxLengthString, handleUserChange("email")),
        [user.email]
    );
    let inputFirstName: any = useMemo( () =>
            getInputFirstNameProps(user.firstName, maxLengthString, handleUserChange("firstName")),
        [user.firstName]
    );
    let inputSurname: any = useMemo(() =>
            getInputSurnameProps(user.surname, maxLengthString, handleUserChange("surname")),
        [user.surname]
    );
    let inputPassword: any = useMemo(() =>
            getInputPasswordProps(user.password, props.passwordMinimumLength, props.passwordMaximumLength,
                handleUserChange("password")),
        [user.password]
    );
    let inputPasswordConfirmation: any = useMemo(() =>
            getInputPasswordConfirmationProps(passwordConfirmation, handlePasswordConfirmationChange),
        [passwordConfirmation]
    );

    const formForEmail: any = {
        label: labelEmail,
        input: inputEmail
    };

    const formForFirstName: any = {
        label: labelFirstName,
        input: inputFirstName
    };

    const formForSurname: any = {
        label: labelSurname,
        input: inputSurname
    };

    const formForPassword: any = {
        label: labelPassword,
        input: inputPassword,
        inputError: getInputErrorPasswordProps(!isPasswordValid, passwordErrorText)
    };

    const formForPasswordConfirmation : any = {
        label: labelPasswordConfirmation,
        input: inputPasswordConfirmation,
        inputError: getInputErrorPasswordConfirmationProps(!isPasswordConfirmationValid)
    };

    const pageProps: any = {
        backendError: backendError,
        handleSubmit: handleSubmit,
        formForEmail: formForEmail,
        formForFirstName: formForFirstName,
        formForSurname: formForSurname,
        formForPassword: formForPassword,
        formForPasswordConfirmation: formForPasswordConfirmation
    };

    return (
        <PageTemplate Component={RegistrationPage} props={pageProps}/>
    );
};