import {FunctionComponent} from "react";
import {EditRatingPage} from "./EditRatingPage";

export const EditRating: FunctionComponent<any> = ({props}) => {
    const id: string = props.id;
    const name: string = "test";
    const description: string = "test";

    const handleSubmit = (event: any) => {
        event.preventDefault();
    };

    return (
        <EditRatingPage name={name} description={description} handleSubmit={handleSubmit}/>
    );
};