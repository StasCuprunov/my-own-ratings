import {FunctionComponent} from "react";
import {LoadComponentHandling} from "../component/LoadComponentHandling";
import {CreateRating} from "./CreateRating";

export const LoadCreateRating: FunctionComponent<any> = () => {
    const { data, error } = {data: "data", error: null};

    return (
        <LoadComponentHandling Component={CreateRating} props={data} error={error}/>
    );
};