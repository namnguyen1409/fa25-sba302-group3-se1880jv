# AdministrativeUnitControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getAdministrativeUnits**](#getadministrativeunits) | **GET** /api/common/administrative-units | |

# **getAdministrativeUnits**
> CustomApiResponseListAdministrativeUnitResponse getAdministrativeUnits()


### Example

```typescript
import {
    AdministrativeUnitControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AdministrativeUnitControllerApi(configuration);

let level: 'PROVINCE' | 'DISTRICT' | 'WARD'; // (default to undefined)
let parentCode: string; // (optional) (default to undefined)

const { status, data } = await apiInstance.getAdministrativeUnits(
    level,
    parentCode
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **level** | [**&#39;PROVINCE&#39; | &#39;DISTRICT&#39; | &#39;WARD&#39;**]**Array<&#39;PROVINCE&#39; &#124; &#39;DISTRICT&#39; &#124; &#39;WARD&#39;>** |  | defaults to undefined|
| **parentCode** | [**string**] |  | (optional) defaults to undefined|


### Return type

**CustomApiResponseListAdministrativeUnitResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

