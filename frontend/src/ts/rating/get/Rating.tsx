import {FunctionComponent, useState} from "react";
import {RatingPage} from "./RatingPage";
import {GridCellParams} from "@mui/x-data-grid";
import {RatingEntry} from "../../model/RatingEntry";

export const Rating: FunctionComponent<any> = ({props}) => {
    const id: string = props.id;

    const [isDeleteRatingDialogOpen, setIsDeleteRatingDialogOpen] =
        useState(false);
    const [isCreateRatingEntryDialogOpen, setIsCreateRatingEntryDialogOpen] =
        useState(false);
    const [isEditRatingEntryDialogOpen, setIsEditRatingEntryDialogOpen] =
        useState(false);
    const [isDeleteRatingEntryDialogOpen, setIsDeleteRatingEntryDialogOpen] =
        useState(false);

    const [backendError, setBackendError] = useState(null);

    const [ratingEntry, setRatingEntry] = useState(new RatingEntry());

    const handleOnCellClick = (params: GridCellParams) => {
        if (params.field === "edit") {
            setRatingEntry(params.row);
            setIsEditRatingEntryDialogOpen(true);
        }
        else if (params.field === "delete") {
            setRatingEntry(params.row);
            setIsDeleteRatingEntryDialogOpen(true);
        }
    };

    const dialogProps: any = {
        ratingId: id,
        setBackendError: setBackendError
    };

    const deleteRatingDialogProps: any = {
        ...dialogProps,
        isOpen: isDeleteRatingDialogOpen,
        setIsOpen: setIsDeleteRatingDialogOpen
    };

    const ratingEntryFormDialogProps: any = {
        ...dialogProps,
        rangeOfValues: props.rangeOfValues,
        ratingEntries: props.ratingEntries,
        maximumLengthOfName: props.maximumLengthOfName
    };

    const createRatingEntryDialogProps: any = {
        ...ratingEntryFormDialogProps,
        isOpen: isCreateRatingEntryDialogOpen,
        setIsOpen: setIsCreateRatingEntryDialogOpen
    };

    const editRatingEntryDialogProps: any = {
        ...ratingEntryFormDialogProps,
        ratingEntry: ratingEntry,
        isOpen: isEditRatingEntryDialogOpen,
        setIsOpen: setIsEditRatingEntryDialogOpen
    };

    const deleteRatingEntryDialogProps: any = {
        ...dialogProps,
        ratingEntry: ratingEntry,
        isOpen: isDeleteRatingEntryDialogOpen,
        setIsOpen: setIsDeleteRatingEntryDialogOpen
    };

    return (
        <RatingPage id={id} name={props.name} description={props.description}
                    rangeOfValues={props.rangeOfValues} ratingEntries={props.ratingEntries}
                    backendError={backendError} handleOnCellClick={handleOnCellClick}
                    deleteRatingDialogProps={deleteRatingDialogProps}
                    createRatingEntryDialogProps={createRatingEntryDialogProps}
                    editRatingEntryDialogProps={editRatingEntryDialogProps}
                    deleteRatingEntryDialogProps={deleteRatingEntryDialogProps}
        />
    );
};