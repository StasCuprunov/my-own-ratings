import {FunctionComponent, useEffect, useState} from "react";
import {LoadComponentHandling} from "../../component/LoadComponentHandling";
import {Rating} from "./Rating";
import {useParams} from "react-router-dom";
import {useRating} from "./RatingFunctions";

export const LoadRating: FunctionComponent<any> = () => {
    const {id} = useParams();
    const {data, error} = useRating(id);
    const [documentTitle, setDocumentTitle] = useState("my rating");

    useEffect(() => {
        let title: string = documentTitle;
        if (data) {
            title = title + ": " + data.name;
        }
        setDocumentTitle(title);
    }, [data]);

    return (
        <LoadComponentHandling Component={Rating} props={data} error={error} documentTitle={documentTitle}
                               needsAuthentication={true}
        />
    );
};