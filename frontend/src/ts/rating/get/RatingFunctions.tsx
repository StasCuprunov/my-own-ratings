import {GridColDef} from "@mui/x-data-grid";
import EditIcon from '@mui/icons-material/Edit';
import ViewListIcon from '@mui/icons-material/ViewList';
import DeleteIcon from "@mui/icons-material/Delete";

import {
    getApiRoutingRatingEntriesDeleteById,
    getApiRoutingRatingsDeleteById,
    getApiRoutingRatingsFindById
} from "../../constant/routing/APIRoutingConstants";
import {useGet} from "../../interface/useGet";
import {CustomButton} from "../../component/atom/button/CustomButton";
import {deleteAxios} from "../../interface/BackendCalls";
import {
    getWebsiteRoutingRatingsEditById,
    WEBSITE_ROUTING_RATINGS
} from "../../constant/routing/WebsiteRoutingConstants";

export const useRating = (id: string | undefined) => {
    return useGet(getApiRoutingRatingsFindById(id));
};

export const deleteRating = async (id: string) => {
    return await deleteAxios(getApiRoutingRatingsDeleteById(id));
};

export const deleteRatingEntry = async (id: string) => {
    return await deleteAxios(getApiRoutingRatingEntriesDeleteById(id));
};

export const goToRatingsButtonProps = () => {
    return {
        text: "Go to ratings",
        to: WEBSITE_ROUTING_RATINGS,
        icon: ViewListIcon
    };
};

export const editRatingButtonProps = (id: string) => {
    return {
        to: getWebsiteRoutingRatingsEditById(id),
        text: "Edit rating",
        icon: EditIcon
    };
};

export const getColumns = (): GridColDef[] => {
    return [
        {
            field: "name",
            headerName: "Name",
            valueGetter: params=> params.row.ratingEntry.name,
            flex: 1
        },
        {
            field: "value",
            headerName: "Value",
            valueGetter: params=> params.row.ratingEntry.value,
            flex: 1
        },
        {
            field: "edit",
            headerName: "Edit",
            sortable: false,
            filterable: false,
            renderCell: editRatingEntryButton,
            flex: 1,
            minWidth: 225
        },
        {
            field: "delete",
            headerName: "Delete",
            sortable: false,
            filterable: false,
            renderCell: deleteRatingEntryButton,
            flex: 1,
            minWidth: 245
        }
    ];
};

const editRatingEntryButton = (params: any) => {
    const props: any = {
        text: "Edit rating entry",
        icon: EditIcon,
        onClick: params.row.handleEditClick
    };

    return (
        <CustomButton props={props}/>
    );
};

const deleteRatingEntryButton = (params: any) => {
    const props: any = {
        text: "Delete rating entry",
        icon: DeleteIcon,
        onClick: params.row.handleDeleteClick
    };

    return (
        <CustomButton props={props}/>
    );
};