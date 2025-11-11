# FileUploadApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**uploadMultiple**](FileUploadApi.md#uploadmultiple) | **POST** /api/files/upload-multiple |  |
| [**uploadSingle**](FileUploadApi.md#uploadsingle) | **POST** /api/files/upload |  |



## uploadMultiple

> CustomApiResponseListString uploadMultiple(entityType, entityId, files)



### Example

```ts
import {
  Configuration,
  FileUploadApi,
} from '';
import type { UploadMultipleRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new FileUploadApi(config);

  const body = {
    // string
    entityType: entityType_example,
    // string
    entityId: entityId_example,
    // Array<Blob>
    files: /path/to/file.txt,
  } satisfies UploadMultipleRequest;

  try {
    const data = await api.uploadMultiple(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **entityType** | `string` |  | [Defaults to `undefined`] |
| **entityId** | `string` |  | [Defaults to `undefined`] |
| **files** | `Array<Blob>` |  | |

### Return type

[**CustomApiResponseListString**](CustomApiResponseListString.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `multipart/form-data`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## uploadSingle

> CustomApiResponseString uploadSingle(entityType, entityId, file)



### Example

```ts
import {
  Configuration,
  FileUploadApi,
} from '';
import type { UploadSingleRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new FileUploadApi(config);

  const body = {
    // string
    entityType: entityType_example,
    // string
    entityId: entityId_example,
    // Blob
    file: BINARY_DATA_HERE,
  } satisfies UploadSingleRequest;

  try {
    const data = await api.uploadSingle(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **entityType** | `string` |  | [Defaults to `undefined`] |
| **entityId** | `string` |  | [Defaults to `undefined`] |
| **file** | `Blob` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseString**](CustomApiResponseString.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `multipart/form-data`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

