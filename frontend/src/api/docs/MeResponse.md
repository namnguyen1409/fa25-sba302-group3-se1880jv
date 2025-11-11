# MeResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**username** | **string** |  | [optional] [default to undefined]
**email** | **string** |  | [optional] [default to undefined]
**phone** | **string** |  | [optional] [default to undefined]
**active** | **boolean** |  | [optional] [default to undefined]
**locked** | **boolean** |  | [optional] [default to undefined]
**mfaEnabled** | **boolean** |  | [optional] [default to undefined]
**userProfile** | [**SimpleProfileResponse**](SimpleProfileResponse.md) |  | [optional] [default to undefined]
**firstLogin** | **boolean** |  | [optional] [default to undefined]
**roles** | [**Set&lt;RoleNameResponse&gt;**](RoleNameResponse.md) |  | [optional] [default to undefined]
**device** | [**DeviceInfo**](DeviceInfo.md) |  | [optional] [default to undefined]
**staff** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]
**patient** | [**PatientResponse**](PatientResponse.md) |  | [optional] [default to undefined]
**room** | [**RoomResponse**](RoomResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { MeResponse } from './api';

const instance: MeResponse = {
    id,
    username,
    email,
    phone,
    active,
    locked,
    mfaEnabled,
    userProfile,
    firstLogin,
    roles,
    device,
    staff,
    patient,
    room,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
