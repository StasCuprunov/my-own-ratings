import {FunctionComponent} from "react";
import {LoadComponentHandling} from "../../component/LoadComponentHandling";
import {EditRating} from "./EditRating";
import {useParams} from "react-router-dom";
import {useRating} from "../useRating";

export const LoadEditRating: FunctionComponent<any> = () => {
    const {data, error} = {data: "", error: null};
    const {id} = useParams();
    return (
        <LoadComponentHandling Component={EditRating} props={data} error={error} />
    );
};