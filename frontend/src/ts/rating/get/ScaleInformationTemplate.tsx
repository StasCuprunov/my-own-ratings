import {FunctionComponent} from "react";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import {CSS_CLASS_TABLE_CONTAINER} from "../../constant/CSSClassNameConstants";

export const ScaleInformationTemplate: FunctionComponent<any> = ({rangeOfValues}) => {
    return (
        <div className={CSS_CLASS_TABLE_CONTAINER}>
            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Attribute</TableCell>
                            <TableCell>Value</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow>
                            <TableCell>Maximum</TableCell>
                            <TableCell>{rangeOfValues.maximum}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Minimum</TableCell>
                            <TableCell>{rangeOfValues.minimum}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Step width</TableCell>
                            <TableCell>{rangeOfValues.stepWidth}</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
};