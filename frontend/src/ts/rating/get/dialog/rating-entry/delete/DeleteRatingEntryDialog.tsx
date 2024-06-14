import {FunctionComponent} from "react";
import {DeleteRatingEntryDialogTemplate} from "./DeleteRatingEntryDialogTemplate";
import {useNavigate} from "react-router-dom";
import {deleteRatingEntry} from "../../../RatingFunctions";
import {WEBSITE_ROUTING_REFRESH} from "../../../../../constant/routing/WebsiteRoutingConstants";

export const DeleteRatingEntryDialog: FunctionComponent<any> = ({props}) => {
    const setIsOpen = props.setIsOpen;
    const navigate = useNavigate();

    const handleClose = (): void => {
        setIsOpen(false);
    };

    const handleDelete = async () => {
        const {error} = await deleteRatingEntry(props.ratingEntry.id);

        if (error) {
            props.setBackendError(error);
            return;
        }
        navigate(WEBSITE_ROUTING_REFRESH);
    };

    const templateProps: any = {
        name: props.ratingEntry?.name,
        isOpen: props.isOpen,
        handleClose: handleClose,
        handleDelete: handleDelete
    };

    return (
        <DeleteRatingEntryDialogTemplate props={templateProps}/>
    );
};