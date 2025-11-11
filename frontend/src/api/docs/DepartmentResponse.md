# DepartmentResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**name** | **string** |  | [optional] [default to undefined]
**description** | **string** |  | [optional] [default to undefined]
**clinic** | [**ClinicSimpleResponse**](ClinicSimpleResponse.md) |  | [optional] [default to undefined]
**rooms** | [**Array&lt;RoomResponse&gt;**](RoomResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { DepartmentResponse } from './api';

const instance: DepartmentResponse = {
    id,
    name,
    description,
    clinic,
    rooms,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
