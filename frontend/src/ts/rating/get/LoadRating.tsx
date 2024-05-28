import {FunctionComponent} from "react";
import {LoadComponentHandling} from "../../component/LoadComponentHandling";
import {Rating} from "./Rating";
import {useParams} from "react-router-dom";
import {useRating} from "./RatingFunctions";

export const LoadRating: FunctionComponent<any> = () => {
    const {id} = useParams();
    const {data, error} = useRating(id);

    return (
        <LoadComponentHandling Component={Rating} props={data} error={error}/>
    );
};