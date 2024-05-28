import {FunctionComponent} from "react";
import {LoadComponentHandling} from "../../component/LoadComponentHandling";
import {EditRating} from "./EditRating";

export const LoadEditRating: FunctionComponent<any> = () => {
    const {data, error} = {data: "", error: null};

    return (
        <LoadComponentHandling component={EditRating} props={data} error={error} />
    );
};