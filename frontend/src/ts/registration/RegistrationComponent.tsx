import {useRegistration} from "./useRegistration";
import {Registration} from "./Registration";
import {ComponentHandling} from "../component/ComponentHandling";
import {FunctionComponent} from "react";

export const RegistrationComponent: FunctionComponent = () =>  {
    let {data, error} = useRegistration();

    return (
        <ComponentHandling error={error} HtmlComponent={Registration} props={data}/>
    );
};