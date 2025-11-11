# LabTestResultResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**labTest** | [**LabTestResponse**](LabTestResponse.md) |  | [optional] [default to undefined]
**status** | **string** |  | [optional] [default to undefined]
**resultValue** | **string** |  | [optional] [default to undefined]
**unit** | **string** |  | [optional] [default to undefined]
**referenceRange** | **string** |  | [optional] [default to undefined]
**remark** | **string** |  | [optional] [default to undefined]
**verifiedBy** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]
**verifiedAt** | **string** |  | [optional] [default to undefined]

## Example

```typescript
import { LabTestResultResponse } from './api';

const instance: LabTestResultResponse = {
    id,
    labTest,
    status,
    resultValue,
    unit,
    referenceRange,
    remark,
    verifiedBy,
    verifiedAt,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
