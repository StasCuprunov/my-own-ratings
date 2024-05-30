import {FunctionComponent, useState} from "react";
import {RatingPage} from "./RatingPage";
import {deleteRating, getDeleteRatingButtonObject} from "./RatingFunctions";
import {useNavigate} from "react-router-dom";
import {WEBSITE_ROUTING_INDEX} from "../../constant/routing/WebsiteRoutingConstants";
import {GridCellParams} from "@mui/x-data-grid";
import {RatingEntry} from "../../model/RatingEntry";

export const Rating: FunctionComponent<any> = ({props}) => {
    const id: string = props.id;

    const navigate = useNavigate();

    const [isDeleteRatingDialogOpen, setIsDeleteRatingDialogOpen] =
        useState(false);
    const [isCreateRatingEntryDialogOpen, setIsCreateRatingEntryDialogOpen] =
        useState(false);
    const [isEditRatingEntryDialogOpen, setIsEditRatingEntryDialogOpen] =
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

    const deleteRatingDialogProps: any = {
        isOpen: isDeleteRatingDialogOpen,
        handleClose: handleClose,
        deleteHandleOnClick: deleteHandleOnClick
    };

    const [ratingEntry, setRatingEntry] = useState(new RatingEntry());

    const handleOnCellClick = (params: GridCellParams) => {
        if (params.field === "edit") {
            setRatingEntry(params.row);
            setIsEditRatingEntryDialogOpen(true);
        }
    };

    const ratingEntryFormDialogProps: any = {
        ratingId: id,
        rangeOfValues: props.rangeOfValues,
        ratingEntries: props.ratingEntries,
        maximumLengthOfName: props.maximumLengthOfName,
        setBackendError: setBackendError
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
    return (
        <RatingPage id={id} name={props.name} description={props.description}
                    rangeOfValues={props.rangeOfValues} ratingEntries={props.ratingEntries}
                    deleteRatingButton={deleteRatingButton} deleteRatingDialogProps={deleteRatingDialogProps}
                    backendError={backendError} handleOnCellClick={handleOnCellClick}
                    createRatingEntryDialogProps={createRatingEntryDialogProps}
                    editRatingEntryDialogProps={editRatingEntryDialogProps}
        />
    );
};