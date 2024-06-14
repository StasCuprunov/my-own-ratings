import {FunctionComponent} from "react";
import {LoadComponentHandling} from "../../../component/LoadComponentHandling";
import {CreateRating} from "./CreateRating";
import {useCreateRatingInfo} from "./CreateRatingFunctions";

export const LoadCreateRating: FunctionComponent<any> = () => {
    const { data, error } = useCreateRatingInfo();

    return (
        <LoadComponentHandling Component={CreateRating} props={data} error={error} documentTitle={"create rating"}
                               needsAuthentication={true}
        />
    );
};