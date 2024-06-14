import {useRegistration} from "./RegistrationFunctions";
import {LoadComponentHandling} from "../component/LoadComponentHandling";
import {Registration} from "./Registration";
import {FunctionComponent} from "react";

export const LoadRegistration: FunctionComponent<any> = () =>  {
    let {data, error} = useRegistration();

    return (
        <LoadComponentHandling error={error} Component={Registration} props={data} documentTitle={"registration"}/>
    );
};