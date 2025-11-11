# StaffScheduleDayOffRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**staffId** | **string** |  | [optional] [default to undefined]
**date** | **string** |  | [optional] [default to undefined]
**startTime** | [**LocalTime**](LocalTime.md) |  | [optional] [default to undefined]
**endTime** | [**LocalTime**](LocalTime.md) |  | [optional] [default to undefined]
**roomId** | **string** |  | [optional] [default to undefined]
**reason** | **string** |  | [optional] [default to undefined]

## Example

```typescript
import { StaffScheduleDayOffRequest } from './api';

const instance: StaffScheduleDayOffRequest = {
    staffId,
    date,
    startTime,
    endTime,
    roomId,
    reason,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
