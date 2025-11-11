# FileManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getFiles**](#getfiles) | **GET** /api/files | |
|[**viewFile**](#viewfile) | **GET** /api/files/view/{id} | |

# **getFiles**
> CustomApiResponseListFileAttachmentResponse getFiles()


### Example

```typescript
import {
    FileManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new FileManagementApi(configuration);

let entityType: string; // (default to undefined)
let entityId: string; // (default to undefined)

const { status, data } = await apiInstance.getFiles(
    entityType,
    entityId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **entityType** | [**string**] |  | defaults to undefined|
| **entityId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseListFileAttachmentResponse**

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

# **viewFile**
> File viewFile()


### Example

```typescript
import {
    FileManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new FileManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.viewFile(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**File**

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

