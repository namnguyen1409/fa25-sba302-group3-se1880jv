# AccountSettingResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**username** | **string** |  | [optional] [default to undefined]
**email** | **string** |  | [optional] [default to undefined]
**active** | **boolean** |  | [optional] [default to undefined]
**mfaEnabled** | **boolean** |  | [optional] [default to undefined]
**firstLogin** | **boolean** |  | [optional] [default to undefined]
**OAuthAccounts** | [**Set&lt;OAuthAccountResponse&gt;**](OAuthAccountResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { AccountSettingResponse } from './api';

const instance: AccountSettingResponse = {
    username,
    email,
    active,
    mfaEnabled,
    firstLogin,
    OAuthAccounts,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
