# InvoiceResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**patient** | [**PatientResponse**](PatientResponse.md) |  | [optional] [default to undefined]
**examination** | [**ExaminationResponse**](ExaminationResponse.md) |  | [optional] [default to undefined]
**invoiceNumber** | **string** |  | [optional] [default to undefined]
**issueDate** | **string** |  | [optional] [default to undefined]
**totalAmount** | **number** |  | [optional] [default to undefined]
**paid** | **boolean** |  | [optional] [default to undefined]
**note** | **string** |  | [optional] [default to undefined]
**items** | [**Set&lt;InvoiceItemResponse&gt;**](InvoiceItemResponse.md) |  | [optional] [default to undefined]
**room** | [**RoomResponse**](RoomResponse.md) |  | [optional] [default to undefined]
**assignedStaff** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { InvoiceResponse } from './api';

const instance: InvoiceResponse = {
    id,
    patient,
    examination,
    invoiceNumber,
    issueDate,
    totalAmount,
    paid,
    note,
    items,
    room,
    assignedStaff,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
