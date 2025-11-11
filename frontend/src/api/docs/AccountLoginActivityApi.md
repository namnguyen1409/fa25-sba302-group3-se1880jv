# AccountLoginActivityApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getLoginHistory**](#getloginhistory) | **POST** /api/account/login-activity/filter | |

# **getLoginHistory**
> CustomApiResponsePageLoginAttemptResponse getLoginHistory(searchFilter)


### Example

```typescript
import {
    AccountLoginActivityApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountLoginActivityApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.getLoginHistory(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageLoginAttemptResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

