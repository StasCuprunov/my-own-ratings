import {FunctionComponent, useEffect, useState} from "react";
import {LoadComponentHandling} from "../../../component/LoadComponentHandling";
import {EditRating} from "./EditRating";
import {useParams} from "react-router-dom";
import {useEditRating} from "./EditRatingFunctions";

export const LoadEditRating: FunctionComponent<any> = () => {
    const {id} = useParams();
    const {data, error} = useEditRating(id);
    const [documentTitle, setDocumentTitle] = useState("edit rating");

    useEffect(() => {
        let title: string = documentTitle;
        if (data) {
            title = title + ": " + data.ratingDTO.name;
        }
        setDocumentTitle(title);
    }, [data]);

    return (
        <LoadComponentHandling Component={EditRating} props={data} error={error} documentTitle={documentTitle}
                               needsAuthentication={true}
        />
    );
};