import {FunctionComponent} from "react";
import {LoadComponentHandling} from "../../../component/LoadComponentHandling";
import {EditRating} from "./EditRating";
import {useParams} from "react-router-dom";
import {useEditRating} from "./EditRatingFunctions";

export const LoadEditRating: FunctionComponent<any> = () => {
    const {id} = useParams();
    const {data, error} = useEditRating(id);

    return (
        <LoadComponentHandling Component={EditRating} props={data} error={error} />
    );
};