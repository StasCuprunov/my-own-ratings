import {FunctionComponent} from "react";
import {LoadComponentHandling} from "../../component/LoadComponentHandling";
import {Rating} from "./Rating";
import {useParams} from "react-router-dom";

export const LoadRating: FunctionComponent<any> = () => {
    const {id} = useParams();
    const {data, error} = {data: "", error: null};

    return (
        <LoadComponentHandling Component={Rating} props={data} error={error}/>
    );
};