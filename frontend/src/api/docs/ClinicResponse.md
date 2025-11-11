# ClinicResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**name** | **string** |  | [optional] [default to undefined]
**description** | **string** |  | [optional] [default to undefined]
**phone** | **string** |  | [optional] [default to undefined]
**email** | **string** |  | [optional] [default to undefined]
**address** | [**AddressResponse**](AddressResponse.md) |  | [optional] [default to undefined]
**departments** | [**Array&lt;DepartmentSimpleResponse&gt;**](DepartmentSimpleResponse.md) |  | [optional] [default to undefined]
**taxCode** | **string** |  | [optional] [default to undefined]
**website** | **string** |  | [optional] [default to undefined]
**accountNumber** | **string** |  | [optional] [default to undefined]
**bankName** | **string** |  | [optional] [default to undefined]

## Example

```typescript
import { ClinicResponse } from './api';

const instance: ClinicResponse = {
    id,
    name,
    description,
    phone,
    email,
    address,
    departments,
    taxCode,
    website,
    accountNumber,
    bankName,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
