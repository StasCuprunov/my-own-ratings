import {
    getApiRoutingRatingEntriesDeleteById,
    getApiRoutingRatingsDeleteById,
    getApiRoutingRatingsFindById
} from "../../constant/routing/APIRoutingConstants";
import {useGet} from "../../interface/useGet";
import {GridColDef} from "@mui/x-data-grid";
import {CustomButton} from "../../component/atom/button/CustomButton";
import {ButtonLink} from "../../component/atom/button/link/ButtonLink";
import {deleteAxios} from "../../interface/BackendCalls";
import {WEBSITE_ROUTING_RATINGS} from "../../constant/routing/WebsiteRoutingConstants";

export const useRating = (id: string | undefined) => {
    return useGet(getApiRoutingRatingsFindById(id));
};

export const deleteRating = async (id: string) => {
    return await deleteAxios(getApiRoutingRatingsDeleteById(id));
};

export const deleteRatingEntry = async (id: string) => {
    return await deleteAxios(getApiRoutingRatingEntriesDeleteById(id));
};

export const goToRatingsButtonLink = () => {
    const props: any = {
        type: "button",
        text: "Go to ratings",
        to: WEBSITE_ROUTING_RATINGS
    };
    return (
        <ButtonLink props={props}/>
    );
};

export const getColumns = (): GridColDef[] => {
    return [
        {
            field: 'name',
            headerName: 'Name',
            width: 400
        },
        {
            field: 'value',
            headerName: 'Value',
            width: 400
        },
        {
            field: 'edit',
            headerName: 'Edit',
            sortable: false,
            filterable: false,
            renderCell: editButton
        },
        {
            field: 'delete',
            headerName: 'Delete',
            sortable: false,
            filterable: false,
            renderCell: deleteButton
        }
    ];
};

const editButton = (params: any) => {
    return (
        <CustomButton props={getEditButtonObject()}/>
    );
};

const getEditButtonObject = () => {
    return {
        type: "button",
        text: "Edit"
    };
};

const deleteButton = (params: any) => {
    return (
        <CustomButton props={getDeleteButtonObject(params.row.id)}/>
    );
};

const getDeleteButtonObject = (id: string) => {
    return {
        type: "button",
        text: "Delete"
    };
};