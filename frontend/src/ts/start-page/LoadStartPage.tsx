import {FunctionComponent} from "react";
import {LoadComponentHandling} from "../component/LoadComponentHandling";
import {StartPage} from "./StartPage";
import {useStartPage} from "./StartPageFunctions";

export const LoadStartPage: FunctionComponent<any> = () => {
    const {data, error} = useStartPage();
    console.log(data);

    return (
        <LoadComponentHandling Component={StartPage} props={data} error={error}/>
    );
};