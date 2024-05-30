import {FunctionComponent, useState} from "react";
import {RatingPage} from "./RatingPage";
import {deleteRating, getCreateRatingEntryButtonObject, getDeleteRatingButtonObject} from "./RatingFunctions";
import {useNavigate} from "react-router-dom";
import {WEBSITE_ROUTING_INDEX} from "../../constant/routing/WebsiteRoutingConstants";

export const Rating: FunctionComponent<any> = ({props}) => {
    const id: string = props.id;

    const navigate = useNavigate();

    const [isDeleteRatingDialogOpen, setIsDeleteRatingDialogOpen] =
        useState(false);

    const [backendError, setBackendError] = useState(null);

    const handleDeleteButtonOnClick = (): void => {
        setIsDeleteRatingDialogOpen(true);
    };

    const handleClose = (): void => {
        setIsDeleteRatingDialogOpen(false);
    };

    const deleteHandleOnClick = async () => {
        const {error} = await deleteRating(id);

        if (error) {
            setBackendError(error);
            return;
        }
        navigate(WEBSITE_ROUTING_INDEX);
    };

    const deleteRatingButton: any = getDeleteRatingButtonObject(handleDeleteButtonOnClick);
    const createRatingEntryButton: any = getCreateRatingEntryButtonObject(id);

    const deleteRatingDialogProps: any = {
        isOpen: isDeleteRatingDialogOpen,
        handleClose: handleClose,
        deleteHandleOnClick: deleteHandleOnClick
    };

    return (
        <RatingPage id={id} name={props.name} description={props.description}
                    rangeOfValues={props.rangeOfValues} ratingEntries={props.ratingEntries}
                    deleteRatingButton={deleteRatingButton} createRatingEntryButton={createRatingEntryButton}
                    deleteRatingDialogProps={deleteRatingDialogProps} backendError={backendError}
        />
    );
};