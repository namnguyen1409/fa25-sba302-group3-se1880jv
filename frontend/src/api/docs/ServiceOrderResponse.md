# ServiceOrderResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**orderCode** | **string** |  | [optional] [default to undefined]
**items** | [**Set&lt;ServiceOrderItemResponse&gt;**](ServiceOrderItemResponse.md) |  | [optional] [default to undefined]
**room** | [**RoomResponse**](RoomResponse.md) |  | [optional] [default to undefined]
**assignedStaff** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]
**status** | **string** |  | [optional] [default to undefined]
**examinationPatient** | [**PatientResponse**](PatientResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { ServiceOrderResponse } from './api';

const instance: ServiceOrderResponse = {
    id,
    orderCode,
    items,
    room,
    assignedStaff,
    status,
    examinationPatient,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
