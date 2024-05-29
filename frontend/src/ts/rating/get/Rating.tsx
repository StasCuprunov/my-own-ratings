import {FunctionComponent, useState} from "react";
import {RatingPage} from "./RatingPage";
import {getCreateRatingEntryButtonObject, getDeleteRatingButtonObject} from "./RatingFunctions";

export const Rating: FunctionComponent<any> = ({props}) => {
    const id: string = props.id;

    const [isDeleteRatingDialogOpen, setIsDeleteRatingDialogOpen] =
        useState(false);

    const handleDeleteButtonOnClick = (): void => {
        setIsDeleteRatingDialogOpen(true);
    };

    const handleClose = (): void => {
        setIsDeleteRatingDialogOpen(false);
    };

    const deleteHandleOnClick = (): void => {

    };

    const deleteRatingButton: any = getDeleteRatingButtonObject(handleDeleteButtonOnClick);
    const createRatingEntryButton: any = getCreateRatingEntryButtonObject(id);

    const deleteRatingDialogProps: any = {
        isOpen: isDeleteRatingDialogOpen,
        handleClose: handleClose,
        deleteHandleOnClick: deleteHandleOnClick,
        cancelHandleOnClick: handleClose
    };

    return (
        <RatingPage id={id} name={props.name} description={props.description}
                    rangeOfValues={props.rangeOfValues} ratingEntries={props.ratingEntries}
                    deleteRatingButton={deleteRatingButton} createRatingEntryButton={createRatingEntryButton}
                    deleteRatingDialogProps={deleteRatingDialogProps}
        />
    );
};